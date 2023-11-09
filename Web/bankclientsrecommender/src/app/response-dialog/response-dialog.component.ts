import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ApiResponse } from '../models/prediction.api';

@Component({
  selector: 'app-response-dialog',
  templateUrl: './response-dialog.component.html',
  styleUrls: ['./response-dialog.component.css']
})
export class ResponseDialogComponent implements OnInit {

  public parsedResponse: ApiResponse = { prediction: '', id: '' };
  public feedbackSubmitted : Boolean = false;

  // Add Output EventEmitter if you want to emit an event to the parent component
  @Output() feedbackGiven: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { response: string },
    private http: HttpClient, // Inject the HttpClient
    private dialogRef: MatDialogRef<ResponseDialogComponent> // Inject the MatDialogRef
  ) {}

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

  giveFeedback(isCorrect: boolean): void {
    const feedbackData = {
      predictionId: this.parsedResponse.id,
      isCorrect: isCorrect
    };

    const feedbackURL = `http://localhost:8081/api/giveFeedback?id=${this.parsedResponse.id}&isCorrect=${isCorrect}`;

    // Send the feedback to the backend
    // URL = http://localhost:8081/api/giveFeedback?id=123&isCorrect=true

    this.http.post(feedbackURL, null)
    .subscribe(
      (result) => {
        console.log('Feedback was given', result);
        this.feedbackGiven.emit(true); // Emit an event if needed
        this.feedbackSubmitted = true;
      },
      (error) => {
        console.error('Error giving feedback:', error);
      }
    );
  }

}
