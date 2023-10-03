import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import forum from '@/views/modules/forum/list'
    import news from '@/views/modules/news/list'
    import shiwurenling from '@/views/modules/shiwurenling/list'
    import shiwuzhaoling from '@/views/modules/shiwuzhaoling/list'
    import shiwuzhaolingLiuyan from '@/views/modules/shiwuzhaolingLiuyan/list'
    import wupinguashi from '@/views/modules/wupinguashi/list'
    import wupinguashiLiuyan from '@/views/modules/wupinguashiLiuyan/list'
    import xuanchuanshipin from '@/views/modules/xuanchuanshipin/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryNews from '@/views/modules/dictionaryNews/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShiwuzhaoling from '@/views/modules/dictionaryShiwuzhaoling/list'
    import dictionaryStatus from '@/views/modules/dictionaryStatus/list'
    import dictionaryYesno from '@/views/modules/dictionaryYesno/list'
    import dictionaryXuanchuanshipin from '@/views/modules/dictionaryXuanchuanshipin/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryNews',
        name: '公告类型名称',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShiwuzhaoling',
        name: '物品类型名称',
        component: dictionaryShiwuzhaoling
    }
    ,{
        path: '/dictionaryStatus',
        name: '物品状态',
        component: dictionaryStatus
    },
	,{
	    path: '/dictionaryXuanchuanshipin',
	    name: '视频类型',
	    component: dictionaryXuanchuanshipin
	}
    ,{
        path: '/dictionaryYesno',
        name: '审核',
        component: dictionaryYesno
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典表',
        component: dictionary
      }
    ,{
        path: '/forum',
        name: '论坛',
        component: forum
      }
    ,{
        path: '/news',
        name: '公告',
        component: news
      }
    ,{
        path: '/shiwurenling',
        name: '失物认领',
        component: shiwurenling
      }
    ,{
        path: '/shiwuzhaoling',
        name: '失物招领',
        component: shiwuzhaoling
      }
    ,{
        path: '/shiwuzhaolingLiuyan',
        name: '失物招领留言',
        component: shiwuzhaolingLiuyan
      }
    ,{
        path: '/wupinguashi',
        name: '物品挂失',
        component: wupinguashi
      }
    ,{
        path: '/wupinguashiLiuyan',
        name: '物品挂失留言',
        component: wupinguashiLiuyan
      }
    ,{
        path: '/xuanchuanshipin',
        name: '宣传视频',
        component: xuanchuanshipin
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
