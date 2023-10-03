
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 失物招领留言
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shiwuzhaolingLiuyan")
public class ShiwuzhaolingLiuyanController {
    private static final Logger logger = LoggerFactory.getLogger(ShiwuzhaolingLiuyanController.class);

    @Autowired
    private ShiwuzhaolingLiuyanService shiwuzhaolingLiuyanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ShiwuzhaolingService shiwuzhaolingService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shiwuzhaolingLiuyanService.queryPage(params);

        //字典表数据转换
        List<ShiwuzhaolingLiuyanView> list =(List<ShiwuzhaolingLiuyanView>)page.getList();
        for(ShiwuzhaolingLiuyanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyan = shiwuzhaolingLiuyanService.selectById(id);
        if(shiwuzhaolingLiuyan !=null){
            //entity转view
            ShiwuzhaolingLiuyanView view = new ShiwuzhaolingLiuyanView();
            BeanUtils.copyProperties( shiwuzhaolingLiuyan , view );//把实体数据重构到view中

                //级联表
                ShiwuzhaolingEntity shiwuzhaoling = shiwuzhaolingService.selectById(shiwuzhaolingLiuyan.getShiwuzhaolingId());
                if(shiwuzhaoling != null){
                    BeanUtils.copyProperties( shiwuzhaoling , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShiwuzhaolingId(shiwuzhaoling.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(shiwuzhaolingLiuyan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shiwuzhaolingLiuyan:{}",this.getClass().getName(),shiwuzhaolingLiuyan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            shiwuzhaolingLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        shiwuzhaolingLiuyan.setInsertTime(new Date());
        shiwuzhaolingLiuyan.setCreateTime(new Date());
        shiwuzhaolingLiuyanService.insert(shiwuzhaolingLiuyan);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shiwuzhaolingLiuyan:{}",this.getClass().getName(),shiwuzhaolingLiuyan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("用户".equals(role))
//            shiwuzhaolingLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShiwuzhaolingLiuyanEntity> queryWrapper = new EntityWrapper<ShiwuzhaolingLiuyanEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyanEntity = shiwuzhaolingLiuyanService.selectOne(queryWrapper);
        shiwuzhaolingLiuyan.setUpdateTime(new Date());
        if(shiwuzhaolingLiuyanEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      shiwuzhaolingLiuyan.set
            //  }
            shiwuzhaolingLiuyanService.updateById(shiwuzhaolingLiuyan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shiwuzhaolingLiuyanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ShiwuzhaolingLiuyanEntity> shiwuzhaolingLiuyanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyanEntity = new ShiwuzhaolingLiuyanEntity();
//                            shiwuzhaolingLiuyanEntity.setShiwuzhaolingId(Integer.valueOf(data.get(0)));   //物品 要改的
//                            shiwuzhaolingLiuyanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shiwuzhaolingLiuyanEntity.setShiwuzhaolingLiuyanText(data.get(0));                    //留言内容 要改的
//                            shiwuzhaolingLiuyanEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            shiwuzhaolingLiuyanEntity.setInsertTime(date);//时间
//                            shiwuzhaolingLiuyanEntity.setUpdateTime(new Date(data.get(0)));          //回复时间 要改的
//                            shiwuzhaolingLiuyanEntity.setCreateTime(date);//时间
                            shiwuzhaolingLiuyanList.add(shiwuzhaolingLiuyanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        shiwuzhaolingLiuyanService.insertBatch(shiwuzhaolingLiuyanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = shiwuzhaolingLiuyanService.queryPage(params);

        //字典表数据转换
        List<ShiwuzhaolingLiuyanView> list =(List<ShiwuzhaolingLiuyanView>)page.getList();
        for(ShiwuzhaolingLiuyanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyan = shiwuzhaolingLiuyanService.selectById(id);
            if(shiwuzhaolingLiuyan !=null){


                //entity转view
                ShiwuzhaolingLiuyanView view = new ShiwuzhaolingLiuyanView();
                BeanUtils.copyProperties( shiwuzhaolingLiuyan , view );//把实体数据重构到view中

                //级联表
                    ShiwuzhaolingEntity shiwuzhaoling = shiwuzhaolingService.selectById(shiwuzhaolingLiuyan.getShiwuzhaolingId());
                if(shiwuzhaoling != null){
                    BeanUtils.copyProperties( shiwuzhaoling , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShiwuzhaolingId(shiwuzhaoling.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shiwuzhaolingLiuyan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ShiwuzhaolingLiuyanEntity shiwuzhaolingLiuyan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shiwuzhaolingLiuyan:{}",this.getClass().getName(),shiwuzhaolingLiuyan.toString());
        shiwuzhaolingLiuyan.setInsertTime(new Date());
        shiwuzhaolingLiuyan.setCreateTime(new Date());
        shiwuzhaolingLiuyanService.insert(shiwuzhaolingLiuyan);
        return R.ok();
        }


}
