package sjjd.doc.line.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sjjd.doc.line.interceptor.AutoInterceptor;
import sjjd.doc.line.interceptor.LogInterceptor;
import sjjd.doc.line.service.IJurUrlService;

import javax.annotation.Resource;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 日志拦截器
     */
    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private AutoInterceptor autoInterceptor;

    @Resource
    private IJurUrlService jurUrlService;


    /**
     * 重写添加拦截器方法并添加配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(logInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/admin/showlogin"
                , "/admin/login"
                , "/admin/loginmessage"
                , "/js/**"
                , "/layui/**"
                , "/login/**"
                , "/font-awesome/**"
                , "/image/**"
                , "/role/roleList"
                , "/role/roleTypeList"
                , "/admin/getRoleselect"
                , "/doctor/docList"
                , "/depart/depList"
                , "/room/roomList"
                , "/depart/crewsetList"
                , "/doctor/AllDoctor"
                , "/time/timeslotList"
                , "/scheduling/watchList"
                , "/voice/voiceList"
                , "/facility/facilityList"
                , "/fullcalendar/**"
                , "/facility/displayList"
                , "/room/getRoomAll"
                , "/doctors/**"
                , "/depart/**"
                , "/depart/departAll"
                , "/facility/addfacility"
                , "/register/**"
                , "/depart/departAlls"
                , "/facility/facInit"
                , "/doctor/docLogin"
                , "/facility/facAll"
                , "/voices/**"
                , "/facility/screenShot"
                , "/screen/**"
                , "/doctor/logout"
                , "/update/**"
                , "/depart/signList"
                , "/depart/getsignList"
                , "/depart/departAllsP"
                , "/notice/NoticeList"
                , "/adminLogin/**"
                , "/program/**"
                , "/temzip/**"
                , "/logRecord/logRecordList"
                , "/mackpri/macpriList"
                , "/mack/MackList"
                , "/mackpri/modiManage"
                , "/mack/modiMack"
                , "/appointment/**"
                , "/report/reportList"
                , "/reports/**"
                , "/report/modiReport"
                , "/swagger-resources/**"
                , "/webjars/**"
                , "/v2/**"
                , "/swagger-ui.html/**"
                , "/MP_verify_v9tBdIzVtMW0yA4X.txt"
                , "/cikkod/**"
                , "/doc.html/**"
                , "/docSche/**"
                , "/reportInfo/**"
                , "/selfHelp/**"
                , "/news/**"
                , "/baseIntro/**"
        );
//        JurUrlExample getJur = new JurUrlExample();
//        List<JurUrl> jurs = jurUrlService.selectByExample(getJur);
//        List<String> list = new ArrayList<>();
//
//        for (JurUrl jurUrl : jurs) {
//            list.add(jurUrl.getAutoUrl());
//        }
//        registry.addInterceptor(autoInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }


}
