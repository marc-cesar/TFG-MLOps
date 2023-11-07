import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-new-request-page',
  templateUrl: './new-request-page.component.html',
  styleUrls: ['./new-request-page.component.css']
})
export class NewRequestPageComponent {

  http = inject(HttpClient);

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
    }, { responseType: 'text' }).subscribe((data) => {
      console.log(data);
    });
  }
}
