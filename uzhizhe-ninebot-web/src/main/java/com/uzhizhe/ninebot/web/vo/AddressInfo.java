package com.uzhizhe.ninebot.web.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Desc address info
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-13
 */
@Data
public class AddressInfo implements Serializable {
    private static final long serialVersionUID = 2996949875044565079L;
    @NotNull(message = "country not can null")
    @Size(min = 3, max = 20, message = "country size 3-20")
    private String country;
    private String shengfen;
    private CityInfo cityInfo;

}
