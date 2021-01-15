module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8082',
        ws: true,
        changeOrigin: true
      }
    }
  },
  outputDir: '../../../target/front/public',
}
