module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    proxy: {
      'http://localhost:8085/api': {
        target: 'http://localhost:8082/api',
        proxyRoot: true,
        ws: true,
        changeOrigin: true
      }
    }

  },
  outputDir: '../../../target/front/public',
}