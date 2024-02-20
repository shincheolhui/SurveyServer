package com.lec.spring.controller;


import com.lec.spring.domain.Survey;
import com.lec.spring.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
// ↑ Lombok 어노테이션으로, 클래스의 필드로 선언된 final 변수들을 이용하여 생성자를 자동으로 생성합니다.
// 이 경우 SurveyService surveyService 필드를 사용한 생성자가 생성됩니다.
@RestController
// ↑ Spring에서 RESTful 웹 서비스의 컨트롤러임을 나타내는 어노테이션입니다. 이 클래스의 모든 핸들러 메서드는 기본적으로 @ResponseBody가 적용된 것으로 처리됩니다.
@CrossOrigin
// ↑ Cross-Origin Resource Sharing (CORS)를 허용하기 위한 어노테이션으로, 다른 도메인에서의 요청도 허용합니다.
@RequestMapping("/survey")
// ↑ 클래스 레벨에서의 요청 매핑을 지정합니다. 이 클래스의 모든 핸들러 메서드는 "/survey" 경로로 접근할 수 있습니다.
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/")
    public ResponseEntity<?> CheckResponseInPostman() {
        return new ResponseEntity<String>("http://localhost:9090/survey/ 응답확인! HttpStatus는? => " + HttpStatus.OK, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> surveyList() {
        return new ResponseEntity<>(surveyService.list(), HttpStatus.OK);
    } // Postman 확인! Postman으로 작성한 데이터 불러오기 확인!

    @PostMapping("/write")
    public ResponseEntity<?> surveyWrite(@RequestBody Survey survey) {
        return new ResponseEntity<>(surveyService.write(survey), HttpStatus.CREATED);
    } // Postman 확인! DB 저장확인!

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> selectSpecificSurvey(@PathVariable Long id) {
        return new ResponseEntity<>(surveyService.selectById(id), HttpStatus.OK);
    } // Postman 확인완료!

    @PutMapping("/update")
    public ResponseEntity<?> updateSurvey(@RequestBody Survey survey) {
        return new ResponseEntity<>(surveyService.update(survey), HttpStatus.OK);
    } // Postman에서 수정되는것 확인완료!

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSurvey(@PathVariable Long id) {
        return new ResponseEntity<>(surveyService.deleteById(id), HttpStatus.OK);
    }

}
