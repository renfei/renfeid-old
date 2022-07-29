import { isNumber } from "./isNumber";
import { isUuid } from "./isUuid";

export const isParameter = (str: string) => isNumber(str) || isUuid(str)
