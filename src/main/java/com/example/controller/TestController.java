package com.example.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.RenderQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bpmn")
public class TestController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam String keyword){
        System.out.println("test");
        return "test";
    }

    @PostMapping(value="/startProcess/{param")
    public boolean startProcess(@PathVariable String param){
        Map<String,Object> map=new HashMap();
        map.put("offerId",param);
        ProcessInstance instance=runtimeService.startProcessInstanceByKey("T20",map);
        if(instance!=null){
            return true;
        }else {
            return false;
        }
    }

    @PostMapping(value="/finishTask/{taskId}")
    public boolean finishTask(@PathVariable String taskId,@RequestParam String userId,@RequestParam String param){
        Map<String,Object> map=new HashMap();
        map.put("param",param);
        List<Task> tasks=taskService.createTaskQuery()
                .taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for(Task task:tasks){
            if(taskId.equals(task.getId())){
                taskService.complete(task.getId(),map);
                return true;
            }
        }
        return false;
    }
}
