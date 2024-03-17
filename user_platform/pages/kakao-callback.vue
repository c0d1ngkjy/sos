<template>
    <div class="h-screen flex flex-col justify-center items-center text-lg">
        <div>인가코드 : <span class="text-red-500">{{ $route.query.code }}</span></div>
    </div>
</template>

<script setup>
//const $route = useRoute();
//console.log($route.query.code)
</script>