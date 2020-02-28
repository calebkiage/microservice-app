var gulp = require('gulp');
var sass = require('gulp-sass');
var browserSync = require('browser-sync').create();
var cleanCSS = require('gulp-clean-css');

function styles() {
    return gulp.src(['sass/*'])
        .pipe(sass())
        .pipe(cleanCSS())
        .pipe(gulp.dest('css/'))
        .pipe(browserSync.stream())
}

function reload(done) {
    browserSync.reload();
    done();
}

function serve(done) {
    browserSync.init({
        open: false,
        port: 8000,
        server: {
            baseDir: './'
        }
    });
    done();
}

function watchSass() {
    return gulp.watch('sass/**', styles);
}

function watchHtml() {
    return gulp.watch('*.html', reload);
}

const dev = gulp.series(styles, serve, gulp.parallel(watchSass, watchHtml));
exports.styles = styles;
exports.default = dev;