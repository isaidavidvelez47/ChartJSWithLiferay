import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'tags',
  styleUrls: ['./tag.component.css'],
  templateUrl: './tag.component.html'
})
export class TagComponent implements OnInit {

  @Input() barChartData;
  @Input() lineChartData;
  @Input() tableData;

  // Tags
  tags = [];
  activeTag = 'Tablas';

  constructor() { }

  ngOnInit() {
    this.setTags();
  }

  setTags() {
    this.tags = [
      { num: 1, text: 'Tablas' },
      { num: 2, text: 'Gráficas' }
    ];
  }

  setActiveTag(newTag) {
    this.activeTag = newTag;
  }

}
