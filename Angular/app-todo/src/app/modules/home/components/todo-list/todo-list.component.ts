import { Component, DoCheck, OnInit } from '@angular/core';
import { TaskList } from '../../model/task-list';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements DoCheck {

  public taskList: Array<TaskList> = JSON.parse(localStorage.getItem("list") || '[]');

  constructor() { }

  ngDoCheck(): void {
    if (this.taskList) {
      this.taskList.sort((first, last) => Number(first.checked) - Number(last.checked));
      localStorage.setItem("list", JSON.stringify(this.taskList));
    }

  }

  public setEmitTaskList(event: string) {
    this.taskList.push({ task: event, checked: false });
  }

  public deleteTaskListItem(event: number) {
    this.taskList.splice(event, 1);
  }

  public deleteAllTaskListItems() {
    const confirm = window.confirm("Do you really want remove all items?");
    if (confirm)
      this.taskList = [];
  }

  public validationInput(event: string, index: number) {
    let confirm = false;

    if (!event.length) {
      confirm = window.confirm("Task is empty! Do you really want delete?");
    }

    if (confirm) {
      this.deleteTaskListItem(index);
    }
  }
}
