// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@nuxt/ui'],
  colorMode: {
    preference: 'light'
  },
  plugins: [
  ],
  runtimeConfig: {
    public: {
      kakaoJsApiKey: '972c8d91b867f0c29eba9f84b5492831',
      kakaoRestApiKey: '2dd24412d30865e308ac6f7e62e26a60'
    }
  },
  tailwindcss: {
    cssPath: ['~/assets/css/tailwind.css', { injectPosition: 'last' }],
    configPath: 'tailwind.config',
    exposeConfig: false,
    config: {},
    viewer: true,
  },
  app: {
    head: {
      meta: [
        // <meta name="viewport" content="width=device-width, initial-scale=1">
        //{ name: 'viewport', content: 'width=device-width, initial-scale=1' }
      ],
      script: [
        // <script src="https://myawesome-lib.js"></script>
        //{ src: 'https://awesome-lib.js' }
        // { src: 'https://developers.kakao.com/sdk/js/kakao.min.js' }
        { src: 'https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js'},
        { src: `//dapi.kakao.com/v2/maps/sdk.js?appkey=972c8d91b867f0c29eba9f84b5492831`}
        //<script src="" integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8" crossorigin="anonymous"></script>
      ],
      link: [
        // <link rel="stylesheet" href="https://myawesome-lib.css">
        //{ rel: 'stylesheet', href: 'https://awesome-lib.css' }
      ],
      // please note that this is an area that is likely to change
      style: [
        // <style type="text/css">:root { color: red }</style>
        //{ children: ':root { color: red }', type: 'text/css' }
      ],
      noscript: [
        // <noscript>JavaScript is required</noscript>
        { children: 'JavaScript is required' }
      ]
    }
  },
  routeRules: {
    '/api/**': { cors: true },
},
})
