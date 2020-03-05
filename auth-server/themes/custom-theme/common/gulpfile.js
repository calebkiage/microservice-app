var gulp = require('gulp');
var sass = require('gulp-sass');
var cleanCSS = require('gulp-clean-css');

function styles() {
    return gulp.src(['sass/*'])
        .pipe(sass())
        .pipe(cleanCSS())
        .pipe(gulp.dest('resources/css/'))
}

function watchSass() {
    return gulp.watch('sass/**', styles);
}

function watchHtml() {
    return gulp.watch('*.html');
}

const dev = gulp.series(styles, gulp.parallel(watchSass, watchHtml));
exports.styles = styles;
exports.default = dev;