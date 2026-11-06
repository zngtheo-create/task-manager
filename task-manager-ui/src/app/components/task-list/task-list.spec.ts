import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { TaskList } from './task-list';
import { TaskService } from '../../task.service';

describe('TaskList', () => {
  let component: TaskList;
  let fixture: ComponentFixture<TaskList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskList],
      providers: [
        provideHttpClient(),
        TaskService
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TaskList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
