package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 宣传视频
 *
 * @author 
 * @email
 */
@TableName("xuanchuanshipin")
public class XuanchuanshipinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XuanchuanshipinEntity() {

	}

	public XuanchuanshipinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "Xuanchuanshipin{" +
            "id=" + id +
            ", xuanchuanshipinName=" + xuanchuanshipinName +
            ", xuanchuanshipinPhoto=" + xuanchuanshipinPhoto +
            ", xuanchuanshipinVideo=" + xuanchuanshipinVideo +
            ", xuanchuanshipinTypes=" + xuanchuanshipinTypes +
            ", xuanchuanshipinContent=" + xuanchuanshipinContent +
            ", createTime=" + createTime +
        "}";
    }
}
