package com.uzhizhe.ninebot.web.logger;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author qingjiang.li
 * @since 2019-07-03 2:14 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
@ToString
public class SysLoggerBo {
    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;

}
