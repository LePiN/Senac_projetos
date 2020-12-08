var gulp = require('gulp');
var concat = require('gulp-concat');

gulp.task('default',['js']);

gulp.task('js', function() {
  return gulp.src([
    './resources/angular/app/app.js',
    './resources/angular/app/directives/*.js',
    './resources/angular/app/controllers/*.js'
  ])
  .pipe(concat('app.js'))
  .pipe(gulp.dest('./resources/angular'))
});
