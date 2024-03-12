// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@nuxt/ui'],
  colorMode: {
    preference: 'light'
  },
  plugins: [
  ],
  tailwindcss: {
    cssPath: ['~/assets/css/tailwind.css', { injectionPosition: 'last' }],
    configPath: 'tailwind.config',
    exposeConfig: false,
    config: {},
    viewer: true,
  }
})
