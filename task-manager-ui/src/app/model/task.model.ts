export interface Task {
  id: number;
  title: string;       // <-- 'title' au lieu de 'titre'
  description?: string;
  completed: boolean;   // <-- 'completed' au lieu de 'complete'
  createdAt: string;
}

export interface TaskRequest {
  title: string;       // <-- 'title' au lieu de 'titre'
  description?: string;
}
