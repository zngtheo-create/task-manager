import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-task-list',
  imports: [DatePipe],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css'
})
export class TaskList {
  tasks: Task[] = [
    {
      id: 1,
      title: 'Apprendre Angular',
      description: 'Terminer le chapitre sur les services',
      completed: false,
      createdAt: '2026-11-03T10:15:30'
    },
    {
      id: 2,
      title: 'Apprendre Spring Boot',
      completed: true,
      createdAt: '2026-11-02T08:00:00'
    },
    {
      id: 3,
      title: 'Installer PostgreSQL',
      description: 'Fait en phase 1',
      completed: true,
      createdAt: '2026-11-01T18:30:00'
    }
  ];
}