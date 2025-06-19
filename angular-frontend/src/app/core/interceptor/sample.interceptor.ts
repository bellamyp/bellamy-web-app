import { HttpInterceptorFn } from '@angular/common/http';

export const sampleInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
  // Hold this sample interceptor to save Angular architecture
};
