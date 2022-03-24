package top.trumandu.common.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import top.trumandu.common.anno.IgnoreRestResult;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
public class ResponseBodyWrapFactoryBean implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() {

        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        assert returnValueHandlers != null;
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(returnValueHandlers);
        adapter.setReturnValueHandlers(handlers);

    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {

        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }

    }
}

class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        RestResult restResult = null;
        Annotation[] annotations = returnType.getAnnotatedElement().getAnnotations();
        if (returnType.getMethodAnnotation(IgnoreRestResult.class) != null) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            return;
        }
        if (returnValue instanceof RestResult) {
            restResult = (RestResult) returnValue;
        } else {
            restResult = new RestResult(returnValue);
        }
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
        assert response != null;
        response.setStatus(restResult.getCode());
        delegate.handleReturnValue(restResult, returnType, mavContainer, webRequest);
    }

}
