<template>
    <div class="flex flex-col gap-16 md:px-40 px-10 py-20">
        <div class="text-xl md:text-3xl font-bold text-center">간단 명료한 솔루션 플랜</div>
        <div class="flex-col md:flex-row flex flex-grow gap-24 justify-between">
            <div :style="plan.name === '프로 플랜' ? '    background: linear-gradient(143deg, #8b5cf6, white);' : ''"
                style="min-height: 400px;" v-for="plan in productPlans"
                class="hover:shadow-md hover:shadow-primary px-8 space-y-4 py-4 border border-primary rounded-lg flex-1 flex flex-col items-start">
                <div class="text-xl font-semibold">{{ plan.name }}</div>
                <div class="text-3xl font-bold">{{ plan.price }}$ / 월</div>

                <div class="flex-grow flex flex-col justify-start gap-3">
                    <div v-for="item in plan.content" class="flex items-center gap-2">
                        <UIcon class="text-lg" name="i-heroicons-check-circle"></UIcon>
                        <div>{{ item }}</div>
                    </div>

                </div>
                <UButton class="" size="xl">{{ plan.btnLabel }}</UButton>
            </div>
        </div>

    </div>
</template>

<script setup>
const productPlans = [
    {
        name: "무료 플랜",
        price: '0',
        content: ['일반 유저 플랫폼에 미용실 등록 및 홍보'],
        btnLabel: '무료 플랜 신청하기'
    },
    {
        name: "프로 플랜",
        price: '300',
        content: ['일반 유저 플랫폼에 미용실 등록 및 홍보', '디자이너 등록 및 관리', '올인원 관리자 플랫폼',
            '예약 시스템', '결제 모듈', '고객 회원 관리',],
        btnLabel: '프로 플랜 신청하기'
    },

]
</script>
