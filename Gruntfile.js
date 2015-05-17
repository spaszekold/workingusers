module.exports = function(grunt) {

    grunt.initConfig({
        includeSource: {
            options: {
                // Task-specific options go here.
            },
            your_target: {
                // Target-specific file lists and/or options go here.
            }
        },
        imagemin: {
            dynamic: {
                files: [{
                    expand: true,
                    cwd: 'src/main/resources/static/img/dev',
                    src: ['**/*.{png,jpg,gif}'],
                    dest: 'src/main/resources/static/img'
                }]
            }
        },
        watch: {
            css: {
                files: ['src/main/resources/static/sass/**/*.scss'],
                tasks: ['sass'],
                options: {
                    spawn: false,
                    livereload: true
                }
            }
        },
        bowerInstall: {
            target: {
                src: [
                    'src/main/resources/templates/**/*.html',
                ],
                cwd: '',
                dependencies: true,
                devDependencies: false,
                exclude: [],
                fileTypes: {},
                ignorePath:  /\.\.\/\.\.\/static/,
                overrides: {}
            }
        },
        sass: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'src/main/resources/static/sass',
                    src: ['**/*.scss'],
                    dest: 'src/main/resources/static/css',
                    ext: '.css'
                }]
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-imagemin');
    grunt.loadNpmTasks('grunt-include-source');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-bower-install');

    grunt.registerTask('default', ['bowerInstall', 'sass', 'includeSource','imagemin']);
};