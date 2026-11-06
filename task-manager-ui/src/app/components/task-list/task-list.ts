import { Component, OnInit, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Task } from '../../model/task.model';
import { TaskService } from '../../task.service';

@Component({
  selector: 'app-task-list',
  imports: [DatePipe],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css'
})
export class TaskList implements OnInit {
  private taskService = inject(TaskService);

  tasks = signal<Task[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks(): void {
    this.loading.set(true);
    this.error.set(null);

    this.taskService.getAll().subscribe({
      next: (data: Task[]) => {
        this.tasks.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Impossible de charger les tâches. Vérifie que le backend tourne sur le port 9090.');
        this.loading.set(false);
      }
    });
  }
}
