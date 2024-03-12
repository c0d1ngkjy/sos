import type { Config } from 'tailwindcss'
import defaultTheme from 'tailwindcss/defaultTheme'

export default <Partial<Config>>{
  theme: {
    extend: {
      colors: {
        sandal: {
          50: '#f6f4f0',
          100: '#e9e3d8',
          200: '#d4c8b4',
          300: '#bca688',
          400: '#af9474',
          500: '#997959',
          600: '#83634b',
          700: '#6a4d3e',
          800: '#5a4239',
          900: '#4f3a34',
          950: '#2c1f1c',
        },

      }
    }
  }
}
