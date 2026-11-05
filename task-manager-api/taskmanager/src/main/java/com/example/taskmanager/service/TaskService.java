package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public Task create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return repository.save(task);
    }

    @Transactional
    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        return repository.save(task);
    }

    @Transactional
    public Task toggle(Long id) {
        Task task = findById(id);
        task.setCompleted(!task.isCompleted());
        return repository.save(task);
    }

    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        repository.delete(task);
    }
}