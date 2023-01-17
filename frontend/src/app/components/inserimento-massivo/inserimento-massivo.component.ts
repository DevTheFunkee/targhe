import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/dataservice';
@Component({
  selector: 'app-inserimento-massivo',
  templateUrl: './inserimento-massivo.component.html',
  styleUrls: ['./inserimento-massivo.component.css']
})
export class InserimentoMassivoComponent implements OnInit {
 fileContent: any;
  constructor(public dataservice: DataService) { }

  ngOnInit(): void {
  }


  public onChange(fileList: FileList): void {
    let file = fileList[0];
    let fileReader: FileReader = new FileReader();
    let self = this;
    fileReader.onloadend = function(x) {
      self.fileContent = fileReader.result;
      self.fileContent = self.fileContent.split(/\r?\n/);
    }
    fileReader.readAsText(file);
  }
  
   ngOnDestroy() {
    this.dataservice.array = this.fileContent; 
  }
}
