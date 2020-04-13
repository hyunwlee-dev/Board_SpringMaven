package com.hwlee.controller;

import com.hwlee.domain.Sample2VO;
import com.hwlee.domain.Ticket;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample2")
@Log4j
public class Sample2Controller {

    @GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
    public String getText() {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "안녕하세요";
    }

    @GetMapping(value = "/getSample",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    public Sample2VO getSample() {
        return new Sample2VO(112, "스타", "로드");
    }

    @GetMapping(value = "/getSample2")
    public Sample2VO getSample2() {
        return new Sample2VO(26, "현우", "이");
    }

    @GetMapping("/getList")
    public List<Sample2VO> getList() {
        return IntStream.range(1, 10).mapToObj(i -> new Sample2VO(i, i + "First", i + "Last"))
                .collect(Collectors.toList());
    }

    @GetMapping("/getMap")
    public Map<String, Sample2VO> getMap() {
        Map<String, Sample2VO> map = new HashMap<>();
        map.put("First", new Sample2VO(26, "현우", "이"));
        return map;
    }

    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<Sample2VO> check(Double height, Double weight) {
        Sample2VO vo = new Sample2VO(0, "" + height, "" + weight);

        ResponseEntity<Sample2VO> result = null;

        if (height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.info("convert....ticket" + ticket);
        return ticket;
    }
}
