<template class="pretendard">
    <div class="flex justify-between items-center px-8 py-2">
        <div class="font-bold text-xl">Salon Search</div>
        <ClientOnly>
            <UButton size="xl" :icon="isDark ? 'i-heroicons-moon-20-solid' : 'i-heroicons-sun-20-solid'" color="gray"
                variant="ghost" aria-label="Theme" @click="isDark = !isDark" />
            <template #fallback>
                <div class="w-8 h-8" />
            </template>
        </ClientOnly>
    </div>

    <hero />
    <service-info />
    <reviews />

    <UDivider></UDivider>
    <div class="relative flex items-center justify-center py-36" style="overflow: hidden;">
        <div class="flex flex-col items-center gap-5">
            <div class="md:text-5xl text-3xl font-bold">지금 바로 등록하세요</div>
            <UButton icon="i-heroicons-rocket-launch" size="xl" color="primary" variant="solid" label="지금 신청하기">
            </UButton>
        </div>

        <div class="absolute top-40 left-0"
            style="z-index: -1; width: 33vw; height: 33vw; border-radius: 15rem; background: linear-gradient(180deg, #341D65 0%, rgba(52, 29, 101, 0) 100%), radial-gradient(94.51% 124.88% at 94.32% 94.43%, rgba(65, 244, 255, 0.78) 0%, rgba(128, 217, 255, 0.78) 46.52%, rgba(65, 198, 255, 0) 100%), linear-gradient(41deg, #FFFCE6 4.33%, rgba(255, 252, 230, 0) 73.7%); background-blend-mode: normal,normal,normal,normal,normal,normal; filter: blur(50px);">
        </div>
        <div class="absolute bottom-60 right-0"
            style="z-index: -1; width: 33vw; height: 33vw; border-radius: 15rem; background: linear-gradient(180deg, #EF516D 0%, rgba(239, 81, 109, 0) 100%), radial-gradient(94.51% 124.88% at 94.32% 94.43%, rgba(65, 244, 255, 0.78) 0%, rgba(131, 218, 255, 0.6552) 32.29%, rgba(99, 175, 240, 0.3978) 64.06%, rgba(43, 90, 211, 0) 100%), linear-gradient(313.04deg, #341D65 0.93%, #604AEA 125.68%); background-blend-mode: normal,normal,normal,normal,normal,normal; filter: blur(50px);">
        </div>
    </div>
    <Footer />
</template>

<script setup lang="ts">

const colorMode = useColorMode()
const isDark = computed({
    get() {
        return colorMode.value === 'dark'
    },
    set() {
        colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark'
    }
})
</script>