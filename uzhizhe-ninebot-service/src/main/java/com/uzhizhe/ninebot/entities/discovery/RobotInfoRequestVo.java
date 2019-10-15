package com.uzhizhe.ninebot.entities.discovery;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc 上报机器人信息的VO
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-09-26
 */
@Data
public class RobotInfoRequestVo implements Serializable {
    private static final long serialVersionUID = -2561368747231377992L;
    private String robotId;//机器人ID
    private Double latitude;//经度
    private Double longitude;//维度
    private Boolean online;//是否在线
    private Boolean locked;//是否已锁
    private Boolean charging;//是否在充电
    private Integer powerPercent;//滑板车当前电量百分比 80->80%
    private Integer speed;//滑板车当前车速 221->22.1km/h
    private Integer odometer;//总骑行里程，单位：m
    private Date statusUtcTime;//车辆状态信息更新时间
    //以下非必传
    private Integer remainingRange;//预计剩余里程，单位10m
    private Integer totalRidingSec;//总骑行时长，单位：秒
    private Integer satelliteNumber;//搜索到的卫星个数
    private Double hdop;//HDOP(定位精度)
    private Float altitude;//海拔高度
    private Date gpsUtcTime;//定位UTC时间

}
