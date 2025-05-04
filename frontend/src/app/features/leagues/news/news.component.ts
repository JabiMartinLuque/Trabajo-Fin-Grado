import { Component } from '@angular/core';
import { NewsDTO } from '../../../dtos/news.dto';
import { NewsService } from './news.service';
import { ActivatedRoute } from '@angular/router';

import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CarouselModule } from 'ngx-owl-carousel-o';

@Component({
  selector: 'app-news',
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule, CarouselModule],
  templateUrl: './news.component.html',
  styleUrl: './news.component.scss'
})
export class NewsComponent {
  news: NewsDTO[] = [];
  isLoading = false;
  errorMessage = '';
  league: string = '';

  constructor(private newsService: NewsService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      // Si usas query params en vez de ruta, usa: params.get('league')
      this.league = params.get('league') || 'eng.1';
      this.loadNews();
    });
  }

  private loadNews(): void {
    this.isLoading = true;
    this.newsService.getNews(this.league).subscribe({
      next: data => {
        this.news = Array.isArray(data) ? data : [data];
        this.isLoading = false;
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Error cargando noticias';
        this.isLoading = false;
      }
    });
  }
}
