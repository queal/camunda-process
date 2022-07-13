package per.queal.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class ExampleController {

    @Autowired
    @Qualifier("remote")
    private RuntimeService runtimeService;

    @Autowired
    @Qualifier("remote")
    private TaskService taskService;

    @Autowired
    @Qualifier("remote")
    private ExternalTaskService externalTaskService;

    @RequestMapping("/test")
    public void test() {
        Console.log("runtimeService.createProcessInstanceQuery().count()", runtimeService.createProcessInstanceQuery().count());
        Console.log("runtimeService.createProcessInstanceQuery().list()", runtimeService.createProcessInstanceQuery().list());
        Console.log("runtimeService.createProcessInstanceQuery().active().count()", runtimeService.createProcessInstanceQuery().active().count());
        Console.log("runtimeService.createProcessInstanceQuery().active().list()", runtimeService.createProcessInstanceQuery().active().list());

        Console.log(StrUtil.repeat("-", 100));
        Console.log("taskService.createTaskQuery().count()", taskService.createTaskQuery().count());
        Console.log("taskService.createTaskQuery().list()", taskService.createTaskQuery().list());
        Console.log("taskService.createTaskQuery().active().count()", taskService.createTaskQuery().active().count());
        Console.log("taskService.createTaskQuery().active().list()", taskService.createTaskQuery().active().list());
    }


    @RequestMapping("/addPs")
    public String addPs() {
        // 创建任务
        Map<String, Object> params = MapUtil.newHashMap();
        params.put("money", "1001");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("action-create-order", params);

        Console.log(processInstance.getId());
        Console.log(processInstance.getTenantId());
        Console.log(processInstance.getProcessDefinitionId());
        Console.log(processInstance.getRootProcessInstanceId());
        Console.log(processInstance.getCaseInstanceId());

        return processInstance.getId();
    }

    @RequestMapping("/completePs")
    public void completePs(String pid) {
        Task task = taskService.createTaskQuery().processInstanceId(pid).singleResult();

        Map<String, Object> params = MapUtil.newHashMap();
        params.put("approved", true);
        params.put("remark", "允许");
        taskService.complete(task.getId(), params);
        return;
    }

}
