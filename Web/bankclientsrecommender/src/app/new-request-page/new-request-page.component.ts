import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-new-request-page',
  templateUrl: './new-request-page.component.html',
  styleUrls: ['./new-request-page.component.css']
})
export class NewRequestPageComponent {

  constructor(private http: HttpClient, public dialog: MatDialog) { }

  applyForm = new FormGroup({
    field0: new FormControl(''),
    field1: new FormControl(''),
    field2: new FormControl(''),
    field3: new FormControl(''),
    field4: new FormControl(''),
    field5: new FormControl(''),
    field6: new FormControl(''),
    field7: new FormControl(''),
    field8: new FormControl(''),
    field9: new FormControl(''),
    field10: new FormControl(''),
    field11: new FormControl(''),
    field12: new FormControl(''),
    field13: new FormControl(''),
    field14: new FormControl(''),
    field15: new FormControl(''),
    field16: new FormControl(''),
    field17: new FormControl(''),
    field18: new FormControl(''),
    field19: new FormControl(''),
  });

  emptyForm(){
    // empty all the fields in the form
    this.applyForm.setValue({
      field0: '',
      field1: '',
      field2: '',
      field3: '',
      field4: '',
      field5: '',
      field6: '',
      field7: '',
      field8: '',
      field9: '',
      field10: '',
      field11: '',
      field12: '',
      field13: '',
      field14: '',
      field15: '',
      field16: '',
      field17: '',
      field18: '',
      field19: '',
    });

  }

  sendForm(){
    //console.log(this.applyForm.value)
    this.http.post('http://localhost:8081/api/predict', {
      "0": [this.applyForm.value.field0],
      "1": [this.applyForm.value.field1],
      "2": [this.applyForm.value.field2],
      "3": [this.applyForm.value.field3],
      "4": [this.applyForm.value.field4],
      "5": [this.applyForm.value.field5],
      "6": [this.applyForm.value.field6],
      "7": [this.applyForm.value.field7],
      "8": [this.applyForm.value.field8],
      "9": [this.applyForm.value.field9],
      "10": [this.applyForm.value.field10],
      "11": [this.applyForm.value.field11],
      "12": [this.applyForm.value.field12],
      "13": [this.applyForm.value.field13],
      "14": [this.applyForm.value.field14],
      "15": [this.applyForm.value.field15],
      "16": [this.applyForm.value.field16],
      "17": [this.applyForm.value.field17],
      "18": [this.applyForm.value.field18],
      "19": [this.applyForm.value.field19]
    }, { responseType: 'text' }).subscribe({
      next: (response) => {
        // console.log(response) {"prediction":"0","id":"16"}, (0 = Good,  1 = Bad)
        this.emptyForm()
        this.dialog.open(ResponseDialogComponent, {
          data: {
            response: response
          }
        });
      },
      error: (error) => {
        this.dialog.open(ResponseDialogComponent, {
          data: {
            response: error
          }
        });
      }
    });
  }
}
