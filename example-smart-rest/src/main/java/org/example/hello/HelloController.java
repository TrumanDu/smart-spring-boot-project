package org.example.hello;

import jakarta.validation.Valid;
import org.example.hello.domain.Hello;
import org.springframework.web.bind.annotation.*;
import top.trumandu.common.base.BaseController;
import top.trumandu.common.domain.Response;
import top.trumandu.common.domain.ResultCodeEnum;

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
    public Response test() {
        return Response.ok();
    }

    @GetMapping("/hello/fail")
    public Response fail() {
        return Response.setResult(ResultCodeEnum.BAD_REQUEST).message("参数错误");
    }

    @PostMapping("/hello/info")
    public Response info(@RequestBody @Valid Hello hello) {
        return Response.ok().message("hello world!");
    }
}
