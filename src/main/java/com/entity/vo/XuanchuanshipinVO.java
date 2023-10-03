package com.entity.vo;

import com.entity.XuanchuanshipinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 宣传视频
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xuanchuanshipin")
public class XuanchuanshipinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 视频标题
     */

    @TableField(value = "xuanchuanshipin_name")
    private String xuanchuanshipinName;


    /**
     * 视频封面
     */

    @TableField(value = "xuanchuanshipin_photo")
    private String xuanchuanshipinPhoto;


    /**
     * 视频
     */

    @TableField(value = "xuanchuanshipin_video")
    private String xuanchuanshipinVideo;


    /**
     * 视频类型
     */

    @TableField(value = "xuanchuanshipin_types")
    private Integer xuanchuanshipinTypes;


    /**
     * 详情
     */

    @TableField(value = "xuanchuanshipin_content")
    private String xuanchuanshipinContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：视频标题
	 */
    public String getXuanchuanshipinName() {
        return xuanchuanshipinName;
    }


    /**
	 * 获取：视频标题
	 */

    public void setXuanchuanshipinName(String xuanchuanshipinName) {
        this.xuanchuanshipinName = xuanchuanshipinName;
    }
    /**
	 * 设置：视频封面
	 */
    public String getXuanchuanshipinPhoto() {
        return xuanchuanshipinPhoto;
    }


    /**
	 * 获取：视频封面
	 */

    public void setXuanchuanshipinPhoto(String xuanchuanshipinPhoto) {
        this.xuanchuanshipinPhoto = xuanchuanshipinPhoto;
    }
    /**
	 * 设置：视频
	 */
    public String getXuanchuanshipinVideo() {
        return xuanchuanshipinVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setXuanchuanshipinVideo(String xuanchuanshipinVideo) {
        this.xuanchuanshipinVideo = xuanchuanshipinVideo;
    }
    /**
	 * 设置：视频类型
	 */
    public Integer getXuanchuanshipinTypes() {
        return xuanchuanshipinTypes;
    }


    /**
	 * 获取：视频类型
	 */

    public void setXuanchuanshipinTypes(Integer xuanchuanshipinTypes) {
        this.xuanchuanshipinTypes = xuanchuanshipinTypes;
    }
    /**
	 * 设置：详情
	 */
    public String getXuanchuanshipinContent() {
        return xuanchuanshipinContent;
    }


    /**
	 * 获取：详情
	 */

    public void setXuanchuanshipinContent(String xuanchuanshipinContent) {
        this.xuanchuanshipinContent = xuanchuanshipinContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
