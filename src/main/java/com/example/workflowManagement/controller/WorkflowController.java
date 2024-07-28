
package com.example.workflowManagement.controller;
import com.example.workflowManagement.entity.Workflow;
import com.example.workflowManagement.service.WorkflowService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping("workflow")
    public String createWorkflow(@RequestBody Workflow workflow) {
        System.out.println("Received Workflow: " + workflow);
        if (workflow.getTasks() != null) {
            System.out.println("Tasks count: " + workflow.getTasks().size());
        } else {
            System.out.println("Tasks is null");
        }
        return workflowService.saveWorkflow(workflow).getId().toHexString();
    }
}
