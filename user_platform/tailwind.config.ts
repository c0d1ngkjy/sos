import type { Config } from 'tailwindcss'

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
        woodsmoke: {
          50: '#f6f6f6',
          100: '#e7e7e7',
          200: '#d1d1d1',
          300: '#b0b0b0',
          400: '#888888',
          500: '#6d6d6d',
          600: '#5d5d5d',
          700: '#4f4f4f',
          800: '#454545',
          900: '#3d3d3d',
          950: '#1d1d1d',
        },
        yellow: {
          50: '#feffe7',
          100: '#faffc1',
          200: '#f9ff86',
          300: '#feff41',
          400: '#fff40d',
          500: '#fee500',
          600: '#d1aa00',
          700: '#a67b02',
          800: '#895f0a',
          900: '#744e0f',
          950: '#442904',
        }
      }
    }
  }
}
