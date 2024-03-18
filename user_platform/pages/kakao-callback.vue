<template>
    <div class="h-screen">
    </div>
</template>

<script setup>
const $route = useRoute();
const $router = useRouter();

const accessToken = useCookie('accessToken')
const refreshToken = useCookie('refreshToken')
accessToken.value = $route.query.accessToken
refreshToken.value = $route.query.refreshToken;

$router.push('/')
</script>