import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiResponse } from '../models/prediction.api';

@Component({
  selector: 'app-response-dialog',
  templateUrl: './response-dialog.component.html',
  styleUrls: ['./response-dialog.component.css']
})
export class ResponseDialogComponent implements OnInit {

  public parsedResponse: ApiResponse = { prediction: '', id: '' };

  constructor(@Inject(MAT_DIALOG_DATA) public data: { response: string }) {}

  ngOnInit(): void {
      this.parseResponse();
  }

  private parseResponse() {
    try {
      // Assuming that data.response is a JSON string
      this.parsedResponse = JSON.parse(this.data.response);
    } catch (error) {
      console.error('Error parsing JSON:', error);
      // Handle error scenario, perhaps assign default values or show a message
      this.parsedResponse = { prediction: '', id: '' };
    }
  }

}
