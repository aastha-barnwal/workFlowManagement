
package com.example.workflowManagement.controller;
import com.example.workflowManagement.entity.Workflow;
import com.example.workflowManagement.service.WorkflowService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    // create and save workflows
    @PostMapping("workflow")
    public String createWorkflow(@RequestBody Workflow workflow) {
        //return the id of workflow
        return workflowService.saveWorkflow(workflow).getId().toHexString();
    }

    // return all workflows till date
    @GetMapping("workflow")
    public List<String> getallWorkflows(){
        List<Workflow> all_flows = workflowService.getAll();
        List<String> flowId = new ArrayList<>();
        for(Workflow flow: all_flows){
            flowId.add(flow.getId().toHexString());
        }
        return flowId;
    }

    // delete a particular workflows by id
    @DeleteMapping("workflow/{workflowId}")
    public void deleteWorkflow(@PathVariable ObjectId workflowId){
        workflowService.deleteWorkflowById(workflowId);
    }
}

// Controller ------->>> Service