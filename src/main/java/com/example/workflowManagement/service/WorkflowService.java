//package com.example.workflowManagement.service;
//
//import com.example.workflowManagement.entity.Task;
//import com.example.workflowManagement.entity.Workflow;
//import com.example.workflowManagement.repository.TaskRepo;
//import com.example.workflowManagement.repository.WorkflowRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;

package com.example.workflowManagement.service;

import com.example.workflowManagement.entity.Task;
import com.example.workflowManagement.entity.Workflow;
import com.example.workflowManagement.repository.TaskRepo;
import com.example.workflowManagement.repository.WorkflowRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WorkflowService {

    @Autowired
    private WorkflowRepo workflowRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Transactional
    public Workflow saveWorkflow(Workflow workflow) {
        try {
            // Save tasks first
            for (Task task : workflow.getTasks()) {
                System.out.println("Saving Task: " + task);
                taskRepo.save(task);
            }

            // Save the workflow with references to the saved tasks
            System.out.println("Saving Workflow: " + workflow);
            return workflowRepo.save(workflow);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Workflow();
    }

    public Optional<Workflow> findWorkflow(ObjectId workId){
        return workflowRepo.findById(workId);
    }
    public List<Workflow> getAll(){
        return workflowRepo.findAll();
    }
    public void deleteWorkflowById(ObjectId id){
        workflowRepo.deleteById(id);
    }
}

