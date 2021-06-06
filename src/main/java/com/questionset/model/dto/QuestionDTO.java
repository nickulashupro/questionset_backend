package com.questionset.model.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class QuestionDTO implements Serializable {
    private String id;

    @NotBlank(message = "题目不能为空")
    private String question;

    @NotBlank(message = "答案不能为空")
    private String answer;

    @NotBlank(message = "选项不能为空")
    private String options;

    private String remind;

    private String typeId;

    private Integer questionType;

    //集合ID
    private String setId;
}
