module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    proxy: {
      '/order/{id}': {
        target: 'http://localhost:8082/',
        ws: true,
        changeOrigin: true
      },
      '/pharmacy': {
        target: 'http://localhost:8082/',
        ws: true,
        changeOrigin: true
      },
      '/pharmacy/{id}': {
        target: 'http://localhost:8082/',
        ws: true,
        changeOrigin: true
      },
      '/download/file/report/{id}': {
        target: 'http://localhost:8082/',
        ws: true,
        changeOrigin: true
      },
      '/messageRabbit': {
        target: 'http://localhost:8082/',
        ws: true,
        changeOrigin: true
      }
    }

  },
  outputDir: '../../../target/front/public',
}