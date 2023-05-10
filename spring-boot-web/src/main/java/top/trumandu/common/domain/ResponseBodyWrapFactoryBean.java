package top.trumandu.common.domain;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import top.trumandu.common.anno.IgnoreRestResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Truman.P.Du
 * @date 2020/04/25
 * @description
 */
@SuppressWarnings("unused")
public class ResponseBodyWrapFactoryBean implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void afterPropertiesSet() throws Exception {

        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        assert returnValueHandlers != null;
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(handlers);
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
    public boolean supportsReturnType(@NotNull MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  @NotNull ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest) throws Exception {
        Response restResult = null;
        if (returnType.getMethodAnnotation(IgnoreRestResult.class) != null) {
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            return;
        }
        if (returnValue instanceof Response) {
            restResult = (Response) returnValue;
        } else {
            restResult = Response.ok().data(returnValue);
        }
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
        assert response != null;
        response.setStatus(restResult.getCode());
        delegate.handleReturnValue(restResult, returnType, mavContainer, webRequest);
    }

}
