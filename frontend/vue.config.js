const path = require('path');
module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/' : './', //deveopment.productions,test모드 중 배포시 루트로, 개발시 상대경로로 설정함.

  outputDir: path.resolve(
    __dirname,
    '../backend/webrtc/src/main/resources/static',
    // '../backend/springboot/src/main/resources/static',
    // '../backend/webrtc/src/main/resources/static', // ubuntu용
  ), //빌드파일을 올릴 곳(디폴트(dist)에서 스프링 부트 static폴더로 )
  devServer: {
    port: 3000, //개발하는 동안 프런트엔드 페이지를 띄우는 포트
    proxy: {
      '/api/*': {
        target: 'http://localhost:8446', // /api 요청을 할때 백엔드 호출
      },
    },
  },

  // chainWebpack: (config) => {
  //   config.module.rules.delete("eslint");
  // },
};
