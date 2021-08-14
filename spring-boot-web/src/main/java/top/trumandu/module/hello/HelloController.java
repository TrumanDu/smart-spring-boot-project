package top.trumandu.module.hello;

import org.springframework.web.bind.annotation.*;
import top.trumandu.common.base.BaseController;
import top.trumandu.common.domain.RestResult;
import top.trumandu.module.hello.domain.Hello;

import javax.validation.Valid;

/**
 * @author Truman.P.Du
 * @date 2021/05/13
 * @description
 */
@RestController
public class HelloController extends BaseController {

    @GetMapping("/hello/")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/error")
    public void error() throws Exception {
        throw new Exception("error");
    }

    @GetMapping("/hello/test")
    public RestResult test() {
        return success();
    }

    @GetMapping("/hello/fail")
    public RestResult fail() {
        return failure("参数错误");
    }

    @PostMapping("/hello/info")
    public RestResult info(@RequestBody @Valid Hello hello){
        return success("hello world!");
    }
}
