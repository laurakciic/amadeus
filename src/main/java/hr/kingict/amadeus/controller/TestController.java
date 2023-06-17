package hr.kingict.amadeus.controller;

import hr.kingict.amadeus.dto.TestDto;
import hr.kingict.amadeus.form.TestForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/test")
public class TestController {

    @GetMapping("get")
    public String testGet() {
        return "OK";
    }

    @GetMapping("/get/{param}")
    public String testGetWithParam(@PathVariable String param) {
        return param;
    }

    @GetMapping("/getResponse")
    public ResponseEntity<String> testResponseGet() {
        return ResponseEntity
                .internalServerError()
                .header("testHeader", "bla bla")
                .body("Greska");
    }

    @GetMapping("/getDto")
    public ResponseEntity<TestDto> testGetDto() {
        TestDto testDto = new TestDto();
        testDto.setName("Ime");
        testDto.setDescription("Opis");

        return ResponseEntity
                .ok()
                .body(testDto);
    }

    @PostMapping("/post")
    public ResponseEntity<TestDto> testPostWithParam(@RequestBody @Valid TestForm testForm) {
        TestDto testDto = new TestDto();
        testDto.setName(testForm.getName());
        testDto.setDescription(testForm.getDescription());

        return ResponseEntity
                .ok()
                .body(testDto);
    }
}
