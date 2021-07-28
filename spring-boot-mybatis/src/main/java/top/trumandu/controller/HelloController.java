package top.trumandu.controller;

import org.springframework.web.bind.annotation.*;
import top.trumandu.core.common.BaseController;
import top.trumandu.core.response.RestResult;
import top.trumandu.model.Hello;

import javax.validation.Valid;

/**
 * @author Truman.P.Du
 * @date 2021/05/13
 * @description
 */
@RestController
@RequestMapping("/hello")
public class HelloController extends BaseController {

    @GetMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/error")
    public void error() throws Exception {
        throw new Exception("error");
    }

    @GetMapping("/test")
    public RestResult test() {
        return success();
    }

    @GetMapping("/fail")
    public RestResult fail() {
        return failure("参数错误");
    }

    @PostMapping("/info")
    public RestResult info(@RequestBody @Valid Hello hello){
        return success("hello world!");
    }
}
