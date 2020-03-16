package com.uzhizhe.ninebot.web.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Desc ValidatorBeanA
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-11
 */
@Data
public class ValidatorBeanA implements Serializable {

    private static final long serialVersionUID = -3334353842826101210L;

    @Range(min = 1, max = 999, message = "Message")
    private Integer id;

    @Size(min = 6, max = 10, message = "名称有误, 要求: 长度6-10")
    private String name;

    @Length(min = 10, max = 20, message = "desc length: 10-20")
    private String desc;

    @Size(min = 2, max = 4, message = "list 要求: 长度2-4")
    private List<String> list;

    @Size(min = 2, max = 4, message = "Map size 2-4")
    private Map<String, String> map;

    @Size(min = 2, max = 4, message = "Array size 2-4")
    private Integer[] array;


    @NotNull
    private Boolean gender;

    private Date date;

    @NotNull(message = "address info not null")
    @Valid
    private AddressInfo addressInfo;

}
