export interface NewsDTO {
    id: string;
    header: string;
    articles: ArticleDTO[];
}

export interface ArticleDTO {
    type: string;
    headline: string;
    description: string;
    images: ImageDTO[];
}

export interface ImageDTO {
    type: string;
    width: number;
    height: number;
    url: string;
}