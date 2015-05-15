module.exports = function(grunt) {

    grunt.initConfig({
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


    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-bower-install');

    grunt.registerTask('default', ['bowerInstall', 'sass']);
};